class ETL
  def self.transform(score)
    assign_each_letter_to_point = method :each_letter_to_point
    Hash[ score.flat_map &assign_each_letter_to_point ]
  end

  def self.each_letter_to_point((points, letters))
    letters.map { |letter| [letter.downcase, points] }
  end
end
