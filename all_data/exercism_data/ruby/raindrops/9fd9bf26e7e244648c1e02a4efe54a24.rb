class Raindrops
    def convert(num)
        strin=""
        if num % 3 == 0
            strin+="Pling"
        end
        if num % 5 == 0
            strin+="Plang"
        end
        if num % 7 == 0
            strin+="Plong"
        end
        if strin!= ""
            strin
        else
            num.to_s()
        end
    end
end
