class Phrase

  attr_reader :statement

  def initialize statement
    @statement = statement
  end

  def word_count
    word_count = {}
    @statement.split.each do |key|
      key = sanitize_and_regularize key
      unless key.empty?
        if word_count.has_key?(key)
          word_count[key] += 1
        else
          word_count[key] = 1
        end
      end
    end
    word_count
  end

  private

  def sanitize_and_regularize key
    key.gsub(%r{[/\;!&+=?~@$%^,:]},'').downcase
  end

end
