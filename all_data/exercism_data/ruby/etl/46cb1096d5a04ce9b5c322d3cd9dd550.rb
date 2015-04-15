class ETL
  class << self
    def transform(score)
      Hash[ score.flat_map &each_letter_to_point ]
    end

    private

    def each_letter_to_point
      proc do |(point, letters)|
        letters.map { |letter| [letter.downcase, point] }
      end
    end
  end
end
