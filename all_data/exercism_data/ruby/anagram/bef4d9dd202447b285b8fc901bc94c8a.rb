class Anagram < Struct.new(:word)
  def match(list)
    down = word.downcase
    gram = down.chars.sort
    list.select do |w|
      candidate = w.downcase
      candidate != down && candidate.chars.sort == gram
    end
  end
end
