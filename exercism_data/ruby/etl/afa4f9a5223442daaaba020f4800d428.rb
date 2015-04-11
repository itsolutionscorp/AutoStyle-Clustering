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
      @inverted_key ||= {}.tap do |hash|
        key.each do |number, letters|
          letters.each do |letter|
            hash[letter.downcase] = number
          end
        end
      end
    end
  end
end
