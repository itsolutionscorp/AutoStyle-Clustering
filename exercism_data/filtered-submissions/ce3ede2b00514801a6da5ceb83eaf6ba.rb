def compute(string1, string2)
      string1.chars.zip(string2.chars).count {|(first,last)| first != last }
   end