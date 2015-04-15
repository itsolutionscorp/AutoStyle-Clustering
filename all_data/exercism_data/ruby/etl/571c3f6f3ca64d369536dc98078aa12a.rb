module ETL
  def self.transform data
    Hash[
      *data.map do |score, letters|
        letters.map { |c| [c.downcase, score] }
      end.flatten
    ]
  end
end
