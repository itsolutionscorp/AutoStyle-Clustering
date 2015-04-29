class Phrase
  def initialize(text)
    @count = nil
    if String === text
      @text = text
    else
      raise ArgumentError.new("Argument is not String")
    end
  end

  def word_count
    if @count.nil?
      count
    end

    @count
  end

  private

  def count
    @count = Hash.new(0)

    @text.scan(/\w+/).each do |word|
      @count[word.downcase] += 1
    end
  end
end
