class Phrase

  def initialize(input)
    @input = input
  end

  def word_count
    word_count = Hash.new(0)

    sanitize(@input).split.each do |word|
      word_count[word] += 1
    end

    word_count
  end

  private

  # removes non-alphanumeric chars,
  # and replaces commas with spaces
  def sanitize(input)
    input.gsub(/[^\w|,|\s]/, '')
         .gsub(',', ' ')
         .downcase
  end

end
