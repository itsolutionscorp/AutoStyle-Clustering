class Words < String
  def count
    counts = Hash.new(0)
    clean_split.each { |word| counts[word] += 1 }
    counts
  end

  private
  def clean_split
    downcase.split(/\W+/)
  end
end
