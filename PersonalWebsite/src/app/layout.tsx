import type { Metadata } from 'next'
import { Urbanist } from 'next/font/google'
import Navbar from './navbar/page'
import './globals.css'

const urbanist = Urbanist({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Jude\'s Portfolio Website',
  description: 'Using Prismic and Next.js to build a portfolio website',
}

export default function RootLayout({children}: {children: React.ReactNode}) {
  return (
    <html lang="en" className="bg-slate-900 text-slate-100">
      <body className={`${urbanist.className}`}>
        {children}
      </body>
    </html>
  )
}
