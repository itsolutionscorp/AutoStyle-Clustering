class Words < String
  def count
    counts = Hash.new(0)
    clean_split.each { |word| counts[word] += 1 }
    counts
  end

  private
  def clean_split
    split.map { |word|
      word.downcase!
      word.gsub!(/\W/, "")
      word
    }.reject(&:empty?)
  end
end
