class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    dictionary_entries.reduce({}) do |sum, item|
      sum[item] ||= 0
      sum[item] = sum[item] + 1
      sum
    end
  end

  private
  def dictionary_entries
    text_with_one_separator.downcase.split(' ')
  end

  def text_with_one_separator
    sanitized_text.gsub(/,/,' ')
  end

  def sanitized_text
    @text.gsub(/[:%&$!^@]/,'')
  end
end
