import type { Metadata } from "next";
import "./globals.css";
import { poppins } from "@/shared/fonts/fonts";
import NavBar from "@/shared/components/navbar/NavBar";
import FooterSection from "@/shared/components/footer/FooterSection";
import React from "react";


export const metadata: Metadata = {
  title: "zeco eats",
  description: "Get your food wherever you want",
};

export default async function RootLayout({
  children,
}: Readonly<{ children: React.ReactNode }>) {
  // const session = await auth();
  // console.log("-----------------");
  // console.log(session);
  // console.log("-----------------");

  return (
    <html lang="en">
      <body className={`${poppins.className} text-sm antialiased`}>
        <NavBar />
        {children}
        <FooterSection />

        {/* <AuthProvider session={session}>
          <ExpiredTokenCheck>
              {children}
          </ExpiredTokenCheck>
        </AuthProvider> */}
      </body>
    </html>
  );
}
