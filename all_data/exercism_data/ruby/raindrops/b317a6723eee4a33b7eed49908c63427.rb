class Raindrops
        def Raindrops.convert(num)
                if num%3 == 0 && num%5 == 0 && num %7 == 0
                        return 'PlingPlangPlong'
                end
                if num%3 == 0 && num %7 == 0
                        return 'PlingPlong'
                end
                if num%3 == 0 && num%5 == 0
                        return 'PlingPlang'
                end
                if num%5 == 0 && num %7 == 0
                        return 'PlangPlong'
                end
                if num%3 == 0
                        return 'Pling'
                end
                if num%5 == 0
                        return 'Plang'
                end
                if num %7 == 0 
                        return 'Plong'
                else
                        return num.to_s 
                end
        end
end
