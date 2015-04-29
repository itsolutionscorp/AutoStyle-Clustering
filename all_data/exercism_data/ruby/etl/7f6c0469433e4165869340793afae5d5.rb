class ETL

  ONE_POINT = ["A", "E", "I", "O", "U", "L", "N", "R", "S", "T"]
  TWO_POINT = ["D", "G"]
  THREE_POINT = ["B", "C", "M", "P"]
  FOUR_POINT = ["F", "H", "V", "W", "Y"]
  FIVE_POINT = ["K"]
  EIGHT_POINT = ["J", "X"]
  TEN_POINT = ["Q", "Z"]

  class << self
    def transform(hash)
      result_hash = {}
      hash.each_pair do |key, vals|
        transformed_array(vals, result_hash)
      end
      result_hash
    end

    private
    def transformed_array(vals, result_hash)
      vals.each do |val|
        result_hash.store(val.downcase, hash_value(val))
      end
    end

    def hash_value(val)
      if ONE_POINT.include?(val)
        1
      elsif TWO_POINT.include?(val)
        2
      elsif THREE_POINT.include?(val)
        3
      elsif FOUR_POINT.include?(val)
        4
      elsif FIVE_POINT.include?(val)
        5
      elsif EIGHT_POINT.include?(val)
        8
      elsif TEN_POINT.include?(val)
        10
      end
    end
  end
end
