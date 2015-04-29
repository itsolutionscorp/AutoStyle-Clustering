class Raindrops
    def self.convert (number)
        storage = []

        i = 2
        n = number

        loop do
            (passed, rest) = n.divmod(i)

            # All that's left is one, push to storage and break
            if passed == 0 && rest == 1
                storage.push(i)
                break
            end

            # n was evenlty divided by i, push i to storage, set n to the result and next
            if rest == 0
                n = passed
                storage.push(i)
                next
            end

            # If we got this far, we're not 1 and not divided evenlty
            # Add 1 to i and see if it's the same as what's left (previous rest)
            # If they're the same, that's all we got left. Push it and break
            i += 1
            if i == n
                storage.push(i)
                break
            end
        end

        # Push different words based on what's in the storage
        contains = []
        contains.push('Pling') if storage.include? 3
        contains.push('Plang') if storage.include? 5
        contains.push('Plong') if storage.include? 7

        # Return the number if no prime was found
        return contains.size > 0 ? contains.join('') : number.to_s 
    end
end
