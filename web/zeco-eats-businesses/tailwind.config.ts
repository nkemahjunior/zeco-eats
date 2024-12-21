import type { Config } from "tailwindcss";

export default {
  content: [
    "./src/features/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/shared/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        background: "rgb(245 245 244)",
        backgroundShade1: "#ebebea",
        backgroundShade2: "#e6e6e5",
        backgroundBorder: "rgb(214 211 209)",
        textColorTint: "#78716c", //text-stone-500
      },
    },
  },
  plugins: [],
} satisfies Config;