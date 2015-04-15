class Raindrops
  def self.convert(number)
    lang = [7, 5, 3]
    answer = []

    lang.each do |alphabet|
      while number.remainder(alphabet) == 0 do
        number /= alphabet
        answer << alphabet
      end
    end

    if answer.empty?
      number.to_s
    else
      answer.uniq.reverse.reduce('') do |sentence, alphabet|
        sentence += {
          3 => 'Pling',
          5 => 'Plang',
          7 => 'Plong'
        }[alphabet]
      end
    end
  end
end
