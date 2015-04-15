class ETL
  def self.transform(input)
    Hash[
      input.flat_map do |score, letters|
        letters.map(&:downcase).product([score])
      end
    ]
  end
end
