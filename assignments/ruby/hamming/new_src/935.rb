                                                                                                                                                                                                                                                               def compute(string1, string2)
    result = []
    char_index = 0
    string1.each_char do |character|
      result << 1 if character != string2[char_index]
      char_index += 1
    end
    result.inject(:+) ? result.inject(:+) : 0
  end