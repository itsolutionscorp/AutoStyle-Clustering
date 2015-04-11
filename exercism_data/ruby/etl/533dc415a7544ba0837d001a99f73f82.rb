class ETL
  def self.transform(old_data)
    new(old_data).transform
  end

  def initialize(old_dataset)
    @old_dataset = old_dataset
  end

  def transform
    old_dataset.each.with_object({}) do |(old_key, old_values), result|
      extract_new_entry(old_key, old_values, result)
    end
  end

  private

  def extract_new_entry(old_key, old_values, result)
    old_values.each do |old_value|
      result[normalize_new_key(old_value)] = old_key
    end
  end

  def normalize_new_key(old_value)
    old_value.downcase
  end

  attr_reader :old_dataset
end
