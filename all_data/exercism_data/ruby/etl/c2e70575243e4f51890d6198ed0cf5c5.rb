class ETL
  class << self
    def transform(score)
      Hash[ score.flat_map &method(:each_letter_to_point) ]
    end

    private

    def each_letter_to_point((point, letters))
      letters.map { |letter| [letter.downcase, point] }
    end
  end
end
