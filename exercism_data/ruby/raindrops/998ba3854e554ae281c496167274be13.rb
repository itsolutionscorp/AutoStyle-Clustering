class Raindrops
  NUM_TO_STR_MAPPER = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(input)
    output = NUM_TO_STR_MAPPER.inject('') do |result, (num, str)|
      if input % num == 0
        result << str
      end
      result
    end

    output == '' ? input.to_s : output
  end
end
