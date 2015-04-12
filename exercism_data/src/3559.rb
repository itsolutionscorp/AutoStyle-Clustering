def compute(string_1, string_2)
    string_1.char.zip(string_2.char).count do |char_1, char_2|
      char_1 != char_2
    end
  end
end