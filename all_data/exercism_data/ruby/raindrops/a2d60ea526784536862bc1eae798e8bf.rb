class Raindrops
    def self.convert(i)
        d = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
        s = d.keys.map {|k| d[k] if i % k == 0} .join
        return s.length > 0 ? s : i.to_s
    end
end
