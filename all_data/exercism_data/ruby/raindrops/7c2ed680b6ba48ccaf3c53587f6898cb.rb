class Raindrops
  NUM_TO_STR_MAPPER = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(input)
    output = NUM_TO_STR_MAPPER.each_with_object('') do |(num, str), result|
      if input % num == 0
        result << str
      end
    end

    output == '' ? input.to_s : output
  end
end
