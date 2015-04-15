class ETL

  # Transforms:
  #   old    = { 'key' => ['VAL1', 'VAL2'] }
  # into:
  #   result = { 'val1' => 'key', 'val2' => 'key'}
  def self.transform(old)
    result = {}

    old.each do |key, val_array|
      val_array.each do |val|
        result[val.downcase] = key
      end
    end

    result
  end


end
