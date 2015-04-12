class Hamming
    def compute(a, b)
        diff_count = 0
        
        a = a[0, b.length-1] if a.length > b.length
        b = b[0, a.length-1] if a.length < b.length

        a = a.split(//)
        b = b.split(//)

        a.zip(b) do |x, y|
            diff_count += 1 unless x == y
        end
        diff_count
    end
end
