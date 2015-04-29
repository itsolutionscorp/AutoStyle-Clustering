def compute(string_a, string_b)
    paired_chars = string_a.each_char.zip(string_b.each_char)
    return paired_chars.reduce(0) do |differences_so_far, char_pair|
      differences_this_time = (char_pair[0] != char_pair[1]) ? 1 : 0
      differences_so_far + differences_this_time
    end
  end
end