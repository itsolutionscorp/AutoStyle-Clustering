class ETL
  class << self
    def transform(score)
      assign_each_letter_to_point = method :each_letter_to_point
      Hash[ score.flat_map &assign_each_letter_to_point ]
    end

    private

    def each_letter_to_point((points, letters))
      letters.map { |letter| [letter.downcase, points] }
    end
  end
end
