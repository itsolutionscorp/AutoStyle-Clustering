#!/usr/bin/env python
# -*- coding: utf-8 -*-


class Bob(object):
    def hey(self, text):
        response = "Whatever."

        if text.endswith("?"):
            response = "Sure."

        if len(text.strip()) == 0:
            response = "Fine. Be that way!"
           
        if text.isupper():
            response = "Woah, chill out!"

        return response

# ---------------------------------------- 

def main():
    pass

if __name__ == "__main__":
    main()
