def compute(s1, s2)
    s1.chars.each_index.inject(0) do |differences, i|
      if s1[i] != s2[i]
        differences + 1
      else
        differences
      end
    end
  end

end