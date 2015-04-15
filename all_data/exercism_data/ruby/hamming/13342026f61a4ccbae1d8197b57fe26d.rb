class Hamming
    def self.compute *args
        (a,b) = args.collect { |x| x.split('') }.sort_by(&:length)

        a.zip(b).inject(0) do |agg, current| 
            if current[0] == current[1]
                agg
            else
                agg + 1
            end
        end
    end
end
