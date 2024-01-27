#include <iostream>
#include <vector>

typedef std::string str;
typedef std::vector<double> doubleArr;
typedef std::vector<std::string> strArr;

/*
 * This function prints the polynomial of a function
 */
void printPolynomial(int coefficients[], int size){
    str output;
    int degree = size - 1;
    int i = 0;
    while(degree != -1){
        if (coefficients[i] <= 0 ) output.append("-");
        else if (i != 0) output.append("+");
        output.append(std::to_string(abs(coefficients[i])));
        if (degree != 0){
            output.append("x^");
            output.append(std::to_string(degree));
        }
        degree--; i++;
    }
    std::cout << output << std::endl;
}

/*
 * This function uses the quadratic formula to calculate the solutions to the problem
 * Works for 2 degree polynomial functions only
 */
strArr quadraticFormula(const int coefficients[]){
    strArr output;
    double a = coefficients[0];
    double b = coefficients[1];
    double c = coefficients[2];
    double result1 = ((-1 * b) + sqrt((b*b) - (4*a*c))) / (2*a);
    double result2 = ((-1 * b) - sqrt((b*b) - (4*a*c))) / (2*a);
    if (result1 == result2){
        std::cout << "There is only one output \n";
        output.push_back("x = " + std::to_string(result1));
        return output;
    }
    output.push_back("x = " + std::to_string(result1));
    output.push_back("x = " + std::to_string(result2));
    return output;
}

/*
 * This method does the synthetic division
 * It first finds the factors of the coefficient of the highest degree and the lowest degree
 * Using the factors of those 2 and the division between the factors of those 2 coefficients
 * we try and calculate all possible values that has the solution
 */
strArr polynomialDivision(const int coefficients[], int size){
    strArr finalResult;
    int degree = size - 1;
    doubleArr factorsCoefficient1, factorsCoefficient2, finalFactors;
    for (int i = 1; i < std::max(coefficients[0], coefficients[size-1]); i++){
        if (coefficients[0] % i == 0 && i <= coefficients[0]){
            factorsCoefficient1.push_back((double) i);
            factorsCoefficient1.push_back((double) (-1*i));
        }
        if (coefficients[size-1] % i == 0 && i <= coefficients[size -1]){
            factorsCoefficient2.push_back((double) i);
            factorsCoefficient2.push_back((double) (-1*i));
        }
    }
    for (double i : factorsCoefficient2){
        for (double j : factorsCoefficient1){
            double result = 0.0;
            for (int k = 0; k < size; k++)
                result += coefficients[k]*pow((i/j),degree-i);
            if (result == 0.0){
                if (std::find(finalFactors.begin(), finalFactors.end(), i/j) != finalFactors.end()){
                    finalFactors.push_back(i/j);
                    finalResult.push_back("x = " + std::to_string(i/j));
                }
            }
        }
    }
    return finalResult;
}

int main() {
    // Get the degree of the function
    std::cout << "Degree of the function: ";
    int degree; std::cin >> degree; degree++;
    // Initialize the array to the size of degree + 1
    // Get the inputs for the coefficients, starting with the highest degree first
    int coefficients[degree];
    std::cout << "Enter the coefficients (Highest degree first):\n";
    for (int i = 0; i < degree; i++)
        std::cin >> coefficients[i];
    // Print the polynomial function
    std::cout << "The polynomial is: ";
    printPolynomial(coefficients, degree);
    // Get all the solutions and then print the solutions one by one
    strArr answers;
    answers = (degree - 1 == 2) ? quadraticFormula(coefficients) : polynomialDivision(coefficients, degree);
    for(const auto & answer : answers)
        std::cout << answer << std::endl;
    return 0;
}