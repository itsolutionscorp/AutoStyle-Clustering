require 'pry'
class ETL 
  def self.transform(input)
    ScrabbleTransformer.new(input).transform
  end
end

class ScrabbleTransformer

  attr_accessor :data
  def initialize(data)
    @data = data
  end
  
  def transform
    @transform ||= data.inject({}) do |result, (score, letters)|
      [letters].flatten.compact.each do |letter|
        result[letter.downcase] = score.to_i
      end
      result
    end
  end
end
