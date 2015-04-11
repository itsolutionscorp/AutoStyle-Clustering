class Fixnum
    def to_roman
        number = self
        output = ""
        if number>=1000
            output<<"M"*((number-(number%1000))/1000)
            number=number%1000
        end
        if number>=500
            if number>=900
                output<<"CM"
                number=number-900
            else
                output+=("D"+("C"*((number-500-number%100)/100)))
                number=number%100
            end
        end
        if number>=100
            if number>=400
                output<<"CD"
                number=number-400
            else
                output<<(("C"*((number-number%100)/100)))
                number=number%100
            end
        end
        if number>=50
            if number>=90
                output<<"XC"
                number=number-90
            else
                output<<("L"+("X"*((number-50-number%10)/10)))
                number=number%10
            end
        end
        if number>=10
            if number>=40
                output<<"XL"
                number=number-40
            else
                output<<(("X"*((number-number%10)/10)))
                number=number%10
            end
        end
        if number>=5
            if number>=9
                output<<"IX"
                number=number-9
            else
                number-=5
                output<<("V")
            end
        end
        if number>=1
            if number==4
                output<<"IV"
            else
                output<<("I"*number)
            end
        end
        return output
    end
end
