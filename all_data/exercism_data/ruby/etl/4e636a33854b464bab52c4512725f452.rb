module ETL
  def self.transform(key)
    ETLTransform.new(key).inverted_key
  end

  class ETLTransform
    attr_reader :key
    def initialize(key)
      @key = key
    end

    def inverted_key
      @inverted_key ||= key.each_with_object({}) do |(number, letters), hash|
        letters.each do |letter|
          hash[letter.downcase] = number
        end
      end
    end
  end
end
