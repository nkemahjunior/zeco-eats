import type { Config } from "tailwindcss";

const config: Config = {
  content: [
    "./src/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],

  theme: {
    extend: {
      backgroundImage: {
        "gradient-radial": "radial-gradient(var(--tw-gradient-stops))",
        "gradient-conic":
          "conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))",
      },

      colors: {
        primary: "#FC8A06",
        secondary: "#03081F",
        secondaryTint: "#12172c",
        background: "rgb(245 245 244)",
        backgroundShade1: "#ebebea",
        backgroundShade2: "#e6e6e5",
        backgroundBorder: "rgb(214 211 209)",
        storeTextColorTint: "#78716c", //text-stone-500
      },

      padding: {
        sm: "1rem",
        md: "2rem",
        lg: "3rem",
        xl: "6rem",
        xxl: "6rem",
      },

      margin: {
        sm: "1rem",
        md: "2rem",
        lg: "3rem",
        xl: "6rem",
        xxl: "6rem",
        Ysm: "2rem",
        // Ymd: "1rem",
        Ylg: "2.5rem",
        YXl: "3rem",
        YXXl: "3.5rem",
      },
    },
  },
  plugins: [],
};
export default config;
