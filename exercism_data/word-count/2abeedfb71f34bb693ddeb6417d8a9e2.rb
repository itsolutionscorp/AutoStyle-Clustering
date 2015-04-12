class Phrase < String
  def word_count
    r = Hash.new 0
    scan(/\w+/){|w| r[w.downcase] += 1 }
    r
  end
end
