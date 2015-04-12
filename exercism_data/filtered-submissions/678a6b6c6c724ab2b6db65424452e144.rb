class Hamming 
    def compute(h1,h2)
        perfect = h1.split("")
        broken = h2.split("")
        if perfect.count > broken.count
            lose = perfect.count - broken.count
            lose.times do |i|
                perfect.pop
            end
        end
        count = 0
        total = 0
        perfect.each do |a|
            if a != broken[count] 
                total+=1
            end
            count+=1
        end
        total
    end
end
