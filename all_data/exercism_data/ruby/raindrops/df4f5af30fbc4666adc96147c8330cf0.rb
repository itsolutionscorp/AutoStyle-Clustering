module Raindrops
  def self.convert(num)

    matching_words = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }

    str = ""
    matching_words.keys.map do |num_key|
      if num % num_key == 0
        str += matching_words.fetch(num_key)
      end
    end

    if str.length > 0
      str
    else
      num.to_s
    end
  end
end
