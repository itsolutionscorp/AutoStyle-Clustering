class Hamming

  def self.compute base, target
    base.chars.each_with_index.inject(0) do |differences, (char, i)|
      break differences unless target[i]
      char == target[i] ? differences : differences += 1
    end
  end

end
