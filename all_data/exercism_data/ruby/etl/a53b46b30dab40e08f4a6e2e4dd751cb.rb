class ETL
  def self.transform(scores)
    new(scores).transform
  end

  def initialize(scores)
    @scores = scores
  end

  def transform
    transformed = @scores.map do |key, values|
      values.map { |value| [ value.downcase, key ] }
    end.flatten
    Hash[*transformed]
  end
end
