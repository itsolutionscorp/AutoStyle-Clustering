class ETL
  def self.transform(old_data)
    ScrabbleDataTransform.new(old_data).transform
  end

  class ScrabbleDataTransform
    attr_reader :old_data

    def initialize(old_data)
      @old_data = old_data
    end

    def transform
      old_data.reduce({}) do |new_data, entry|
        words = entry.last
        score = entry.first

        words.each { |word| new_data[sanitize_word(word)] = score }
        new_data
      end
    end

    private

    def sanitize_word(word)
      word.downcase
    end
  end
end
