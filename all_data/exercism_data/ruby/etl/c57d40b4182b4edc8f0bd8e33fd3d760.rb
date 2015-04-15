class ETL
  def self.transform(old_data)
    new(old_data).transform
  end

  def initialize(old_data)
    @old_data = old_data
  end

  def transform
    @old_data.each_with_object({}) do |(old_key, old_values), new_data|
      old_values.each do |old_value|
        new_data[normalize_key(old_value)] = old_key
      end
    end
  end

  private

  def normalize_key(key)
    key.downcase
  end
end
