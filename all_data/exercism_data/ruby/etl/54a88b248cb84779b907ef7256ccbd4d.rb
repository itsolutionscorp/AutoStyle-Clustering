class ETL
  def self.transform(input)
    Hash[input.map { |score, letters| letters.map(&:downcase).product([score]) }
              .flatten(1)]
  end
end
