require 'contracts'

class ETL
  include Contracts

  Contract Hash => Hash
  def self.transform(old_format)
    new_format = {}

    old_format.each_pair do |score, letters|
      letters.each do |letter|
        new_format[letter.downcase] = score
      end
    end

    return new_format
  end
end
