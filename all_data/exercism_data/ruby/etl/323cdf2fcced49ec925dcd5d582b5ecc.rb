class ETL
  def initialize(legacy_hash)
    @legacy_hash = legacy_hash
  end

  def self.transform(legacy_hash)
    new(legacy_hash).transform
  end

  def transform
    new_hash = {}

    @legacy_hash.each_pair do |key, values|
      values.each { |value| new_hash[normalize(value)] = normalize(key) }
    end

    new_hash
  end

  private

  def normalize(value)
    value.is_a?(String) ? value.downcase : value
  end
end
