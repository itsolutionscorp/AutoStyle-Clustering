class ETL
  def self.transform old
    Hash[etl_transform(old)]
  end

  private

  def self.etl_transform old
    old.map do |key, values|
      associate_key_and_value(key, values)
    end.inject(:+)
  end

  def self.associate_key_and_value key, values
    values.map { |value| [value.downcase]+[key] }
  end
end
