class Hamming
    def compute a, b
        (shorter, longer) = [a,b].collect { |x| x.split('') }.sort_by(&:length)

        shorter.zip(longer).count do |current| 
            current[0] != current[1]
        end
    end
end
