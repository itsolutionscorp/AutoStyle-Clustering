class Phrase
  def initialize(message)
    @message = message
  end

  def word_count
    cleaned_message = clean(@message)
    hashify(cleaned_message)
  end

  private

  def hashify(message)
    hash = Hash.new(0)
    words_found = parse(message)
    words_found.each do |word|
      hash[word] += 1
    end
    hash
  end

  def parse(message)
    message.split(" ")
  end

  def clean(message)
    message.to_s
    message.strip!
    message.downcase!
    message.gsub!(",", " ")
    message.gsub!(/[^a-zA-Z0-9\s]+/, "")
    message
  end

end
