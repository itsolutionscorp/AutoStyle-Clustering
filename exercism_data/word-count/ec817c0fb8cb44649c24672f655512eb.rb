class Words
  def initialize phrase
    @phrase = phrase
  end

  def count
    clean
    split
    report
  end

  private
  def clean
    @cleansed_phrase = @phrase.gsub(/[^\w]/, " ").squeeze(" ").downcase
  end

  def split
    @locutions = @cleansed_phrase.split(" ")
  end

  def report
    @locutions.each_with_object({}) { |word, h| h[word] = h[word].to_i + 1 }
  end
end
