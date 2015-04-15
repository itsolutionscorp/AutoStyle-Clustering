class Hamming
    def self.compute(seg_a, seg_b)
        count = 0
        iter_times = seg_a.length <= seg_b.length ? seg_a.length : seg_b.length
        iter_times.times do |index|
            if seg_a[index] != seg_b[index]
                count += 1
            end
        end
        count
    end
end
