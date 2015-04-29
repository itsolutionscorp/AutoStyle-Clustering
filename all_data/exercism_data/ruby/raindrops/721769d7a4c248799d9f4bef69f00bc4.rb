class Raindrops
  class << self

    def convert(num)
      sounds_for(num) || num.to_s
    end

    private

    def sounds_for(num)
      sounds = ""
      sounds += "Pling" if (num % 3).zero?
      sounds += "Plang" if (num % 5).zero?
      sounds += "Plong" if (num % 7).zero?

      sounds.empty? ? nil : sounds
    end
  end
end
