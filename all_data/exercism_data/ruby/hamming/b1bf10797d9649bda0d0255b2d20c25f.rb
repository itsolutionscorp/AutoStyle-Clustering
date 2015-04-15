class Hamming
    def self.compute a, b
        str_to_char_array = Proc.new do |x|
            x.chars.to_a
        end

        (a, b) = [a,b].collect(&str_to_char_array)

        (shorter, longer) = [a,b].sort_by(&:length)

        shorter.zip(longer).count do |current| 
            current[0] != current[1]
        end
    end
end
