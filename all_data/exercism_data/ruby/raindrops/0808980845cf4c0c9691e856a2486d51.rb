class Raindrops

  def self.convert(num)

    hash = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    div_check = false
    output = ""

    hash.each do |key, value|
      div_check = true if num % key == 0
    end

    if !(div_check)
      output = num
    else
      hash.each do |prime, string|
        if num % prime == 0
          output += string
        end
      end
    end
    output.to_s
  end
end
