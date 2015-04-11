class Anagram < Struct.new(:word)
  def match(list)
    down = word.downcase
    gram = down.split(//).sort
    list.select do |w|
      candidate = w.downcase
      candidate != down && candidate.split(//).sort == gram
    end
  end
end
